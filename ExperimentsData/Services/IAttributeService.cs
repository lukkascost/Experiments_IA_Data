using System;
using System.Collections.Generic;
using ExperimentsData.Models.DTO;

namespace ExperimentsData.Services
{
    public interface IAttributeService
    {
        List<AttributeRegisterDto> getAll(Guid sampleId);
        AttributeRegisterDto create(Guid sampleId, AttributeRegisterDto registerDto);
    }
}